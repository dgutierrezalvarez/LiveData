package com.example.livedata;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.livedata.databinding.FragmentEntrenadorBinding;


public class RonaldoFragment extends Fragment {

    private FragmentEntrenadorBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentEntrenadorBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RonaldoViewModel ronaldoViewModel = new ViewModelProvider(this).get(RonaldoViewModel.class);


        ronaldoViewModel.jugadorLiveData.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer imagen) {
                Glide.with(RonaldoFragment.this).load(imagen).into(binding.ejercicio);
            }
        });
    }
}