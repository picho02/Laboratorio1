package com.example.laboratorio1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.VideoView
import androidx.fragment.app.Fragment
import com.example.laboratorio1.databinding.FragmentTrailerBinding
import com.example.laboratorio1.ui.clases.Peli

class TrailerFragment : Fragment() {
    lateinit var binding: FragmentTrailerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTrailerBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tmp = arguments?.getSerializable("peli") as Peli
        binding.tvNombre.text = tmp.nombre
        binding.tvGenero.text = tmp.genero
        binding.tvLanzamiento.text = tmp.estreno
        binding.tvSipnosis.text = tmp.sipnosis

        when (tmp.poster) {
            "posterjurassicworld" -> {
                binding.ivPoster.setImageResource(R.drawable.posterjurassicworld)
                binding.ivBaner.setImageResource(R.drawable.bannerjurassicworld)
                binding.vvTrailer.setVideoPath("android.resource://"+activity?.getPackageName()+"/"+R.raw.trailerjurassicworld)
            }
            "posterspidy"->{
                binding.ivPoster.setImageResource(R.drawable.posterspidy)
                binding.ivBaner.setImageResource(R.drawable.bannerspidy)
                binding.vvTrailer.setVideoPath("android.resource://"+activity?.getPackageName()+"/"+R.raw.spidy)
            }
            "posterstrange"->{
                binding.ivPoster.setImageResource(R.drawable.posterstrange)
                binding.ivBaner.setImageResource(R.drawable.bannerstrange)
                binding.vvTrailer.setVideoPath("android.resource://"+activity?.getPackageName()+"/"+R.raw.trailerstrange)
            }
            "posteranimales"->{
                binding.ivPoster.setImageResource(R.drawable.posteranimales)
                binding.ivBaner.setImageResource(R.drawable.banneranimales)
                binding.vvTrailer.setVideoPath("android.resource://"+activity?.getPackageName()+"/"+R.raw.traileranimales)
            }
        }
        val mc: MediaController = MediaController(requireContext())
        mc.setAnchorView(binding.vvTrailer)
        binding.vvTrailer.setMediaController(mc)
    }


}
