package com.techyourchance.coroutines.demonstrations.structuredconcurrency.java;


import java.math.BigInteger;

import androidx.annotation.WorkerThread;

class FibonacciUseCaseAsyncUiThreadPoster {


    public interface Callback {
        public void onFibonacciComputed(BigInteger result);
    }





    @WorkerThread
    private BigInteger computeFibonacciBg(int index) {
        if (index == 0) {
            return new BigInteger("0");
        } else if (index == 1) {
            return new BigInteger("1");
        } else {
            return computeFibonacciBg(index - 1).add(computeFibonacciBg(index - 2));
        }
    }

    private void notifyResult(BigInteger result, Callback callback) {

    }
}
