public class KMP {
    public int[] getNext(char[] p) {
        // 已知next[j] = k,利用递归的思想求出next[j+1]的值
        // 如果已知next[j] = k,如何求出next[j+1]呢?具体算法如下:
        // 1. 如果p[j] = p[k], 则next[j+1] = k + 1;
        // 2. 如果p[j] != p[k], 则令k=next[k],如果此时p[j]==p[k],则next[j+1]=k+1,
        // 如果不相等,则继续递归前缀索引,令 k=next[k],继续判断,直至k=-1(即k=next[0])或者p[j]=p[k]为止
        int pLen = p.length;
        int[] next = new int[pLen];
        int k = -1;
        int j = 0;
        next[0] = -1; // next数组中第一位next[0]为-1
        while (j < pLen - 1) {
            if (k == -1 || p[j] == p[k]) {
                k++;
                j++;
                next[j] = k;
            } else {
                k = next[k];
            }
        }
        return next;
    }

    public int[] myGetNext(char[] p) {
        int length=p.length;
        int[] next=new int[length];
        next[0]=-1;
        int k=-1;
        int j=0;
        while(j<length-1){
            if(k==-1||p[j]==p[k]){
                k++;
                j++;
                next[j]=k;
            }
            else{
                k=next[k];
            }
        }
        return next;
    }


    public boolean subString(String origin, String pattern) {
        int[] next=myGetNext(pattern.toCharArray());
        int pCursor=0,originCursor=0;
        while(pCursor<pattern.length()&&originCursor<origin.length()){
            if(origin.charAt(originCursor)==pattern.charAt(pCursor)){
                pCursor++;
                originCursor++;
            }
            else{
                int nowNext=next[pCursor];
                if(nowNext==-1){
                    originCursor++;
                    pCursor=0;
                }
                else{
                    pCursor=nowNext;
                }
            }
        }
        if(pCursor==pattern.length()){
            System.out.println(origin.substring(originCursor-pattern.length(),originCursor));
            System.out.println(originCursor-pattern.length());;
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        KMP kmp=new KMP();
        System.out.println(kmp.subString("mississippi","issi"));

    }
}
