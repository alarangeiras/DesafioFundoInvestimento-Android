package br.com.allanlarangeiras.desafioorama.activities.funds.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class ListFundsTabAdapter(
    fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager) {

    private val fragmentList = mutableListOf<Fragment>()
    private val titleList = mutableListOf<String>()

    fun addFragment(fragment: Fragment, title: String) {
        this.fragmentList.add(fragment)
        this.titleList.add(title)
    }


    override fun getItem(position: Int): Fragment {
        return this.fragmentList.get(position)
    }

    override fun getCount(): Int {
        return this.fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return this.titleList.get(position)
    }
}