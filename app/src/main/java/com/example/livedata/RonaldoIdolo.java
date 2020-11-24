package com.example.livedata;

import androidx.lifecycle.LiveData;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.SECONDS;

public class RonaldoIdolo {


    interface futbolListener {
        void cuandoDeLaOrden(String orden);
    }

    Random random = new Random();
    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    ScheduledFuture<?>marcando;

    void iniciarJugador(final futbolListener futbolListener) {
        if (marcando == null || marcando.isCancelled()) {
            marcando = scheduler.scheduleAtFixedRate(new Runnable() {
                int cosa;

                @Override
                public void run() {
                    cosa = random.nextInt(4);    // 0 1 2 3

                    if(cosa == 0){
                        futbolListener.cuandoDeLaOrden("GOL");
                    } else if(cosa == 1){
                        futbolListener.cuandoDeLaOrden("CELEBRACION");
                    } else if(cosa == 2){
                        futbolListener.cuandoDeLaOrden("SALUDO");
                    } else if(cosa == 3){
                        futbolListener.cuandoDeLaOrden("GUIÑO");
                    }
                }
            }, 0, 5, SECONDS);
        }
    }

    void pararJugador() {
        if (marcando != null) {
            marcando.cancel(true);
        }
    }
    LiveData<String> ordenLiveData = new LiveData<String>() {
        @Override
        protected void onActive() {
            super.onActive();

            iniciarJugador(new futbolListener() {
                @Override
                public void cuandoDeLaOrden(String orden) {
                    postValue(orden);
                }
            });
        }

        @Override
        protected void onInactive() {
            super.onInactive();

            pararJugador();
        }
    };
}
