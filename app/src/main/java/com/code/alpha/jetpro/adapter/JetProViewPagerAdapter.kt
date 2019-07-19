package com.code.alpha.jetpro.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class JetProViewPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    private var fragmentList: MutableList<Fragment> = mutableListOf()
    private var fragmentTitle: MutableList<String> = mutableListOf()

    override fun getItem(position: Int): Fragment = fragmentList[position]

    override fun getCount(): Int = fragmentList.size

    fun addTab(fragment: Fragment, title: String) {
        fragmentList.add(fragment)
        fragmentTitle.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? = fragmentTitle[position]
}