package com.santosgo.mavelheroes.ui.herodetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.santosgo.mavelheroes.databinding.FragmentHeroDetailsBinding


class HeroDetailsFragment : Fragment() {

    companion object {
        const val DRAWABLE = "drawable"
    }

    private var _binding : FragmentHeroDetailsBinding? = null
    private val binding
        get() = _binding!!

    val args: HeroDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHeroDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val hero = Datasource.getHero(args.name)

        hero?.let {
            binding.tvName.text = it.name
            binding.ivPhoto.setImageResource(requireContext().resources.getIdentifier(it.photo, DRAWABLE,requireContext().packageName))
            binding.rbPower.rating = hero.power.toFloat()/2
            binding.rbIntelligence.rating = hero.intelligence.toFloat()/2
            binding.tvDesc.text = hero.description
        }

        binding.btnBack.setOnClickListener {
            val action = HeroDetailsFragmentDirections.actionHeroDetailsFragmentToHeroListFragment()
            findNavController().navigate(action)
        }

    }

}