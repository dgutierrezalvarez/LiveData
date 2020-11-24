package com.example.livedata;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

public class RonaldoViewModel extends AndroidViewModel {
    RonaldoIdolo ronaldoIdolo;

    LiveData<Integer> jugadorLiveData;
    LiveData<String> repeticionLiveData;

    public RonaldoViewModel(@NonNull Application application) {
        super(application);

        ronaldoIdolo = new RonaldoIdolo();

        jugadorLiveData = Transformations.switchMap(ronaldoIdolo.ordenLiveData, new Function<String, LiveData<Integer>>() {

            String jugadorAnterior;

            @Override
            public LiveData<Integer> apply(String orden) {



                    int imagen;
                    switch (orden) {
                        case "GOL":
                        default:
                            imagen = R.drawable.gol;
                            break;
                        case "CELEBRACION":
                            imagen = R.drawable.giphy;
                            break;
                        case "SALUDO":
                            imagen = R.drawable.lope;
                            break;
                        case "GUIÑO":
                            imagen = R.drawable.rony;
                            break;
                    }

                    return new MutableLiveData<>(imagen);
                }

        });

    }

}