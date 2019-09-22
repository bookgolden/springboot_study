package com.java.redis;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;

public class MyTest {

//    @Test
//    public void myTest(){
//        /* 创建 Mock 对象 */
//        List list = mock(List.class);
//        /* 设置预期，当调用 get(0) 方法时返回 "111" */
//        when(list.get(0)).thenReturn("111");
//        when(list.get(1)).thenReturn("2222");
//
//        Assert.assertEquals("asd", 1, 1);
//        /* 设置后返回期望的结果 */
//        System.out.println("====set==="+list.get(0));
//        /* 没有设置则返回 null */
//        System.out.println("====unset==="+list.get(1));
//        System.out.println("====unset==="+list.get(2));
//
//        /* 对 Mock 对象设置无效 */
//        list.add("12");
//        list.add("123");
//        System.out.println("==list.toString==="+list.toString());
//        /* 返回之前设置的结果 */
//        System.out.println("list.get(0) = " + list.get(0));
//        /* 返回 null */
//        System.out.println("list.get(1) = " + list.get(1));
//        /* size 大小为 0 */
//        System.out.println("list.size() = "+list.size());
//
//        /* 验证操作，验证 get(0) 调用了 2 次 */
//        verify(list, times(2)).get(0);
//
//        /* 验证返回结果 */
//        String ret = (String)list.get(0);
//        System.out.println("ret = "+ ret);
//        Assert.assertEquals(ret, "111");
//    }
//
//    @Test
//    public void test04(){
//
//        List<String> list = mock(ArrayList.class);
//
//        list.add("一次");
//
//        list.add("两次");
//        list.add("两次");
//
//        list.add("三次");
//        list.add("三次");
//        list.add("三次");
//
//
//        // 下面的两个验证函数效果一样,因为verify默认验证的就是times(1)
//        verify(list).add("一次");
//        verify(list, times(1)).add("一次");
//
//        // 验证具体的执行次数
//        verify(list, times(2)).add("两次");
//        verify(list, times(3)).add("三次");
//
//        // 使用never()进行验证,never相当于times(0)
//        verify(list, never()).add("零次");
//
//        // 使用atLeast()/atMost()
//        verify(list, atLeastOnce()).add("三次");//至少一次
//        verify(list, atLeast(1)).add("三次");//最少1次
//        verify(list, atMost(5)).add("三次");//最多5次
//
//        list.clear();//冗余的调用，未做验证和处理
//        verifyNoMoreInteractions(list);
//    }
//
//
//    @Test
//    public void test05(){
//        List<String> list = mock(ArrayList.class);
//        doThrow(RuntimeException.class).when(list).clear();
//        list.clear();//java.lang.RuntimeException
////        list.clear();//java.lang.RuntimeException
//    }
//
//    @Test
//    public void test06(){
//        List<String> list = mock(ArrayList.class);
//
//        list.add("第一步");//1
//        list.add("第三步");//2，没有4会验证失败
//        list.add("第二步");//3
//        list.add("第三步");//4
//
//        //创建Inorder对象
//        InOrder inOrder = inOrder(list);
//
//        inOrder.verify(list).add("第一步");
//        inOrder.verify(list).add("第二步");
//        inOrder.verify(list).add("第三步");
//    }
//
//    @Mock
//    private Set<String> set;
//
//    @Test
//    public void test07() {
//        //在运行测试函数之前需要被调用
//        MockitoAnnotations.initMocks(this);
//
//        set.add("张三");
//        System.out.println(set.size());//0
////        for (String s : set) {
////            System.out.println("--set---"+ s);
////        }
//    }
//
//    @Test
//    public void test08() {
////        List<String> list = mock(ArrayList.class);
////
////        when(list.get(0)).thenReturn("张三", "李四", "王五");
////
////        //连续的打桩
////        System.out.println(list.get(0));//张三
////        System.out.println(list.get(0));//李四
////        System.out.println(list.get(1));//王五
////        System.out.println(list.get(0));//王五
//
//
//        //创建Mock对象
//        List<String> list = mock(ArrayList.class);
//
//        //打桩
//        when(list.get(0)).then(invocation -> "张三");
//
//        System.out.println(list.size());//0
//        System.out.println(list.get(0));//张三
//
//    }
//
//    @Test
//    public void test09() {
//        List<String> list = mock(ArrayList.class);
//
//        when(list.add(anyString())).thenAnswer(invocation -> {
//            Object[] args = invocation.getArguments();
//            Object mock = invocation.getMock();
//            Method method = invocation.getMethod();
//            System.out.println("参数: " + args[0] + "\n描述：" + mock + "\n方法：" + method + "\n");
//        /*
//        参数: 张三
//        描述：Mock for ArrayList, hashCode: 90320863
//        方法：public boolean java.util.ArrayList.add(java.lang.Object)
//         */
//            return true;
//        });
//
//        System.out.println(list.add("张三"));
//    }


}
