package wang.haogui.yuanda.component;

import org.apache.commons.lang.CharUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @whg
 * 敏感词过滤
 * 将敏感词写入SensitiveWord.txt
 * 在过滤时注入此对象，调用filter方法可以进行过滤
 */
@Component
public class SensitiveFilter implements InitializingBean {

    //构造根节点
    private TrieNode rootNode = new TrieNode();

    /**
     * 在初始化为bean的时候运行的代码
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("SensitiveWord.txt");
        InputStreamReader in = new InputStreamReader(inputStream,"UTF-8");
        BufferedReader bufferedReader = new BufferedReader(in);
        String readLine;
        while((readLine = bufferedReader.readLine()) != null){
            addWord(readLine.trim());
        }
        bufferedReader.close();
    }

    //在树中添加敏感词
    private void addWord(String lineText){
        TrieNode tempNode = rootNode;

        for(int i=0 ; i<lineText.length() ; i++){

            Character c = lineText.charAt(i);
            if(isSymBol(c)){
                continue;
            }
            TrieNode node = tempNode.getSubNode(c);

            if(node == null){
                node = new TrieNode();
                tempNode.addNode(c,node);
            }
            tempNode = node;

            if(i == lineText.length()-1){
                tempNode.setKeyWordEnd(true);
            }
        }

    }

    /**
     * 过滤掉敏感词汇，
     * @param text
     * @return
     */
    public String filter(String text){

        if(StringUtils.isBlank(text)){
            return text;
        }

        String repleacement = "***";
        TrieNode tempNode =  rootNode;
        //引索词所指向的当前的位置
        int position = 0;
        //引索词第一个所在的位置
        int begin = 0;
        //当敏感词相互包含时记录前一个敏感词的位置例如 abc，ab，tempPosion指向b
        int tempPosion=0;
        StringBuffer result = new StringBuffer();

        //是敏感词中的一个词posion继续往后走，不是begin往前走，但完全符合某一个敏感词，且这个敏感词后面不与嵌套的敏感词时一致时替换
        //意思可能会有2个敏感词 ab，abc 符合ab而不符合abc时才替换
        while (position<text.length()){
            Character c = text.charAt(position);

            if(isSymBol(c)){
                if(tempNode == rootNode){
                    result.append(c);
                    ++begin;
                }
                ++position;
                continue;
            }

            tempNode = tempNode.getSubNode(c);

            if(tempNode == null){//不是敏感词
                if(tempPosion != 0){//如果敏感词所在位置不等于0(以前找到了敏感词但由于同时符合另一个所以没有立即替换).先把之前符合敏感词替换掉,随后将position放于tempPosition的位置
                    result.append(repleacement);
                    //跳到敏感词前面去
                    position = tempPosion+1;
                    begin = position;
                    tempNode = rootNode;
                    tempPosion = 0;
                }
                result = result.append(text.charAt(begin));
                //跳到引索词的第一个字符的前一个
                position = begin + 1;
                begin = position;
                tempNode = rootNode;

            }else if (tempNode.isKeyWordEnd()){//是敏感词(但有可能存在敏感词嵌套ab，abc),且这棵树没有子节点了，或者他的子节点不包含敏感词，则立马替换否则继续往后

                if(position+1 < text.length()){//如果数组没有越界的话
                    //这棵树后面没有子节点了，或者子节点不是敏感词
                    if(tempNode.getSubNode(text.charAt(position+1)) == null){
                        result.append(repleacement);
                        //跳到敏感词前面去
                        position = position+1;
                        begin = position;
                        tempNode = rootNode;
                        //将前面的敏感词位置清空
                        tempPosion = 0;
                    }else {
                        //记录这个敏感词的位置，随后继续往后引索
                        tempPosion = position;
                        position++;
                    }
                }else{//越界直接替换
                    result.append(repleacement);
                    //跳到敏感词前面去
                    position = position+1;
                    begin = position;
                    tempNode = rootNode;
                }
            }else{
                ++position;
            }
        }

        return result.toString();

    }

    public boolean isSymBol(char c){
        int ic = (int) c;
        //东亚文字0x2E80~0x9FFF
        return !CharUtils.isAsciiAlphanumeric(c) && (ic<0x2E80 || ic>0x9FFF);
    }



    //前缀树
    private class TrieNode{
        //是不是敏感词的结尾
        private boolean isEnd = false;

        //当前节点下的全部子节点
        private Map subNode = new HashMap<Character,TrieNode>();

        public void addNode(Character key, TrieNode node){
            subNode.put(key,node);
        }

        public TrieNode getSubNode(Character key){
            return (TrieNode) subNode.get(key);
        }

        public boolean isKeyWordEnd(){
            return isEnd;
        }

        public void setKeyWordEnd( boolean isEnd){
            this.isEnd = isEnd;
        }
    }


//    public static void main(String[] args){
//        SensitiveService sensitiveService = new SensitiveService();
//        sensitiveService.addWord("色情");
//        sensitiveService.addWord("色情狂人");
//        sensitiveService.addWord("ab");
//        sensitiveService.addWord("abcd");
//        System.out.println(sensitiveService.filter("你好，色 情 狂 人你,谁呀,a b ce，asd"));
//    }


}
