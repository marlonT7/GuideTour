package com.example.marlon.guidetour

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.os.Bundle


private const val ARG_PARAM1 = "category"

class CategoryAdapter(private val context: Context, fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    /**
     * Return the [Fragment] that should be displayed for the given page number.
     */
    override fun getItem(position: Int): Fragment {
        val places = PlacesListFragment()
        val bundle = Bundle()

        bundle.putInt(ARG_PARAM1, position)
//        val fragInfo = FragmentClass()
       // fragInfo.setArguments(bundle)
        places.arguments = bundle
        return places
    }

    /**
     * Return the total number of pages.
     */
    override fun getCount(): Int {
        return 5
    }



    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> context.getString(R.string.restaurant)
            1 -> context.getString(R.string.river)
            2 -> context.getString(R.string.park)
            3 -> context.getString(R.string.beach)
            else -> context.getString(R.string.lake)
        }
    }
}