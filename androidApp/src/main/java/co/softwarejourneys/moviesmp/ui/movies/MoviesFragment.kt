package co.softwarejourneys.moviesmp.ui.movies

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

import androidx.fragment.app.viewModels


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MoviesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MoviesFragment : Fragment() {
    private val movieMoviesViewModel: MoviesViewModel by viewModels()


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        movieMoviesViewModel.initialize(requireActivity().application)

        val view = inflater.inflate(co.softwarejourneys.moviesmp.android.R.layout.activity_movies, container, false)

        val tabLayout = view.findViewById<TabLayout>(co.softwarejourneys.moviesmp.android.R.id.tabLayout)
        val viewPager = view.findViewById<ViewPager>(co.softwarejourneys.moviesmp.android.R.id.viewPager)

        val adapter = MyPagerAdapter(childFragmentManager)
        adapter.addFragment(AllMoviesFragment(), "Movies")
        adapter.addFragment(FavoriteMoviesFragment(), "Favorites")
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.setTabTextColors(android.graphics.Color.parseColor("#7DC1C6"), resources.getColor(
            co.softwarejourneys.moviesmp.android.R.color.white))

        return view
    }



    private class MyPagerAdapter(manager: FragmentManager) : FragmentStatePagerAdapter(manager) {
        private val fragmentList = mutableListOf<Fragment>()
        private val fragmentTitleList = mutableListOf<String>()

        fun addFragment(fragment: Fragment, title: String) {
            fragmentList.add(fragment)
            fragmentTitleList.add(title)
        }

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return fragmentTitleList[position]
        }
    }
}