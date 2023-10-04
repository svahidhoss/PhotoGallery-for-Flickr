package com.bignerdranch.android.photogallery2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bignerdranch.android.photogallery2.databinding.FragmentPhotoPageBinding

/**
 * A simple [Fragment] subclass.
 */
class PhotoPageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment using view binding
        val binding = FragmentPhotoPageBinding.inflate(inflater, container, false)
        return binding.root
    }
}
