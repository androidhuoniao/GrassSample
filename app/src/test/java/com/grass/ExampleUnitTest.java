package com.grass;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.example.anno.SampleType;
import com.grass.fragment.MainSampleListFragment;

import android.support.v4.app.Fragment;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testClass() {
        Class sampleclass = MainSampleListFragment.class;
        Class superclass = sampleclass.getSuperclass();
        System.out.println(superclass.toString());
        System.out.println(superclass.getSuperclass().toString());
        if (superclass == Fragment.class) {
            assert true;
        }
    }

    @Test
    public void testType() {
        System.out.println("type 0 "+ SampleType.values()[0]);
        System.out.println("type 1 "+ SampleType.values()[1]);
    }
}