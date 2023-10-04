package com.bignerdranch.android.photogallery2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.bignerdranch.android.photogallery2.databinding.FragmentPhotoPageBinding

/**
 * A simple [Fragment] subclass.
 */
class PhotoPageFragment : Fragment() {

    private val args: PhotoPageFragmentArgs by navArgs()

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment using view binding
        val binding = FragmentPhotoPageBinding.inflate(inflater, container, false)
        binding.apply {
            webView.apply {
                settings.javaScriptEnabled = true
                webViewClient = WebViewClient()
                loadUrl(args.photoPageUri.toString())
            }
        }
        return binding.root
    }
}
