package com.gze.sort.number;

/**
 * 排序方式：升序
 * 线性的数值排序：计数排序、基数排序、桶排序
 * 可替换数据类型：byte/short/int/long
 * 最坏时间复杂度：O(n + k)、O(n * k)、O(n^2)
 * 平均时间复杂度：O(n + k)、O(n * k)、O(n + k)
 * 平均空间复杂度：O(n + k)、O(n + k)、O(n + k)
 */
public class LinearSort {

    private HigherSort hs;

    public LinearSort(){
        hs = new HigherSort();
    }
    /**
     * 计数排序，稳定
     * @param array 待排序的数组
     */
    public void countingSort(int[] array){
        int len = array.length;
        if (len > 1){
            //数组的上下边界
            int max = array[0];
            int min = array[0];
            for (int i = 1;i < len;i++){
                if (max < array[i]){
                    max = array[i];
                }
                if (min > array[i]){
                    min = array[i];
                }
            }

            //建立辅助数组并将array数组的元素在bucket记录
            int[] bucket = new int[max - min + 1];
            for (int j = 0;j < len;j++){
                bucket[array[j] - min]++;
            }

            //为了保证稳定性，逆序输出
            for (int k = bucket.length - 1;k >= 0;k--){
                while (bucket[k] > 0){
                    array[--len] = k + min;
                    bucket[k]--;
                }
            }
        }
    }

    /**
     * 基数排序
     * @param array 待排序的数组
     */
    public void radixSort(int[] array){
        int len = array.length;
        if (len > 1){
            //创建辅助数组
            int[] temp = new int[len];
            //确定上下边界
            int min = array[0];
            int max = array[0];
            for (int i = 1;i < len;i++){
                if (min > array[i]){
                    min = array[i];
                } else if (max < array[i]){
                    max = array[i];
                }
            }
            //默认基数数组的长度，-9到0，或0到9，或-9到9
            int radixLen = 10;
            if (min < 0 && max > 0){
                radixLen = 19;
            }
            //求最大的绝对值并赋值给max
            if (min + max < 0){
                max = -1 * min;
            }
            //基数数组的最小元素赋值给min
            if (min < 0){
                min = -9;
            }else {
                min = 0;
            }
            radixSort(array,temp,min,max,radixLen,1);
            if (isCopy(max)){
                for (int i = 0;i < len;i++){
                    array[i] = temp[i];
                }
            }
        }
    }

    /**
     * 基数排序
     * @param array 待排序的数组
     * @param temp  辅助数组
     * @param min   基数数组的最小基数
     * @param max   绝对值最大的数
     * @param radixLen  基数数组长度
     * @param mod   10的倍数
     */
    private void radixSort(int[] array,int[] temp,int min,int max,int radixLen,int mod){
        if (mod < max){
            //建立基数数组，在这里建立省得多次调用时清零
            int[] radix = new int[radixLen];
            //将array数组的元素在radix中计数
            for (int i = 0;i < array.length;i++){
                radix[array[i] / mod % 10 - min]++;
            }
            //累计元素次数，定位元素位置（索引+1）
            for (int j = 1;j < radix.length;j++){
                radix[j] += radix[j - 1];
            }
            //逆序输出，保证元素的稳定
            for (int k = array.length - 1;k >= 0 ;k--){
                if (radix[array[k] / mod % 10 - min] > 0){
                    temp[radix[array[k] / mod % 10 - min] - 1] = array[k];
                    //位置右移一位，方便下一个满足的元素定位
                    radix[array[k] / mod % 10 - min]--;
                }
            }
            radixSort(temp,array,min,max,radixLen,10 * mod);
        }
    }

    /**
     * 判断原始数组是否需要从辅助数组复制
     * @param max 绝对值最大的数
     * @return 原始数组需要复制返回true，反之false
     */
    private boolean isCopy(int max){
        boolean flag = false;
        while (max > 0){
            flag = !flag;
            max /= 10;
        }
        return flag;
    }

    /**
     * 桶排序，计数排序的改进版
     * @param array 待排序的数组
     */
    public void bucketSort(int[] array){
        int len = array.length;
        if (len > 1){
            //确定上下边界
            int min = array[0];
            int max = array[0];
            for (int i = 1;i < len;i++){
                if (min > array[i]){
                    min = array[i];
                } else if (max < array[i]){
                    max = array[i];
                }
            }
            //选择最小的值作为桶，减少空桶数量。假如均匀分布，除以2以便每个桶有两个以上元素
            int bucketLen = Math.min(len,max - min + 1) / 2;
            //每个桶的数值范围，[min,min+mod-1]，[min+mod,min+2*mod-1]...
            int mod = (max - min) / bucketLen + 1;
            int[] bucket = new int[bucketLen];
            int[] temp = new int[len];
            bucketSort(array,bucket,temp,min,mod,len,bucketLen);
        }
    }

    /**
     * 桶排序
     * @param array 待排序的数组
     * @param bucket   桶数组
     * @param temp  临时数组
     * @param min   原始数组最小元素
     * @param mod   每个桶的最大范围，如[min,mod+min-1]
     * @param len 原始数组长度
     * @param bucketLen 桶数组长度
     */
    private void bucketSort(int[] array,int[] bucket,int[] temp,int min,int mod,int len,int bucketLen){
        //每个桶记录符合条件的个数
        for (int i = 0;i < len;i++){
            bucket[(array[i] - min) / mod]++;
        }
        //累加元素位置
        for (int j = 1;j < bucketLen;j++){
            bucket[j] += bucket[j - 1];
        }
        //倒序输出到temp
        for (int k = len - 1;k >= 0;k--){
            if (bucket[(array[k] - min) / mod] > 0){
                temp[bucket[(array[k] - min) / mod] - 1] = array[k];
                bucket[(array[k] - min) / mod]--;
            }
        }
        //分别对桶内元素排序
        for (int a = 1;a < bucketLen;a++){
            hs.quickSort(temp,bucket[a - 1],bucket[a]);
        }
        hs.quickSort(temp,bucket[bucketLen-1],bucketLen - 1);
        //将temp元素拷贝到array中
        for (int b = 0;b < len;b++){
            array[b] = temp[b];
        }
    }
}
