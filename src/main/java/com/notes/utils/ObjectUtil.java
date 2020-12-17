package com.notes.utils;

import com.notes.java.tuple.Tuple2;
import com.notes.java.tuple.Tuple3;
import org.apache.lucene.util.RamUsageEstimator;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class ObjectUtil {

	public Tuple3<Long, Long, String> size(Object o) {
		//计算指定对象本身在堆空间的大小，单位字节
		long shallowSize = RamUsageEstimator.shallowSizeOf(o);
		//计算指定对象及其引用树上的所有对象的综合大小，单位字节
		long size = RamUsageEstimator.sizeOf(o);
		//计算指定对象及其引用树上的所有对象的综合大小，返回可读的结果，如：2KB
		String humanSize = RamUsageEstimator.humanSizeOf(o);
		return new Tuple3(shallowSize, size, humanSize);
	}

	public static void main(String[] args) {
        ObjectUtil objectUtil = new ObjectUtil();
		Tuple3<Long, Long, String> size = objectUtil.size(Tuple3.of(1, 1, ""));
		System.out.println(size);
	}

	public static Long multiSize(List<Tuple2<Object, Long>> object2Size) {
		AtomicLong memAll = new AtomicLong();
		object2Size.forEach(t -> {
			Object object = t._1().get();
			Long size = t._2().get();
			if (object == null
					|| size == null
					|| size == 0) {
				return;
			}
			long l = RamUsageEstimator.shallowSizeOf(object);
			long mem = l * size;
			memAll.addAndGet(mem);
		});
		return memAll.get();
	}

//	public static void main(String[] args) {
//		ATest a = ATest.builder().a("1").build();
//		BTest b = BTest.builder().a("1").b(1).c(newArrayList(1L, 2L, 3L, 4L, 5L, 6L)).build();
//		List<Tuple2<Object, Long>> calculate = newArrayList();
//		calculate.add(Tuple2.of(a, 10L));
//		calculate.add(Tuple2.of(b, 100L));
//		Long aLong = multiSize(calculate);
//		System.out.println(aLong);
//	}

}

//@Builder
//class ATest {
//
//	private String a;
//
//}
//
//@Builder
//class BTest {
//
//	private String a;
//	private Integer b;
//	private List<Long> c;
//}
