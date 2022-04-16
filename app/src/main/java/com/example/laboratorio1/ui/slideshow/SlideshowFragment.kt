package com.example.laboratorio1.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.laboratorio1.R
import com.example.laboratorio1.databinding.FragmentHomeBinding
import com.example.laboratorio1.databinding.FragmentSlideshowBinding
import com.example.laboratorio1.ui.clases.Peli
import com.example.laboratorio1.ui.slideshow.adapter.PeliAdapter

class SlideshowFragment : Fragment(),PeliAdapter.OnItemListener {
    private var _binding: FragmentSlideshowBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var listPelis = ArrayList<Peli>()
        val nombres: Array<String> = resources.getStringArray(R.array.nombres)
        val generos: Array<String> = resources.getStringArray(R.array.genero)
        val sipnosis: Array<String> = resources.getStringArray(R.array.sipnosis)
        val estrenos: Array<String> = resources.getStringArray(R.array.estreno)
        val posters: Array<String> = resources.getStringArray(R.array.poster)
        val trailers: Array<String> = resources.getStringArray(R.array.trailer)
        val banners: Array<String> = resources.getStringArray(R.array.banner)
        for (i in nombres.indices){
            val Tmp = Peli(nombres[i] ,generos[i],sipnosis[i],estrenos[i], posters[i],trailers[i],banners[i])
            listPelis.add(Tmp)
        }
        val adapter = PeliAdapter(listPelis,this)
        with(binding){
            //recyclerview requiere un layout
            rvEstrenos.layoutManager = LinearLayoutManager(requireContext())
            rvEstrenos.adapter=adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(peli: Peli) {
        var bundle = bundleOf("peli" to peli)
        view?.let { Navigation.findNavController(it).navigate(R.id.action_nav_slideshow_to_trailerFragment,bundle) }
    }
}