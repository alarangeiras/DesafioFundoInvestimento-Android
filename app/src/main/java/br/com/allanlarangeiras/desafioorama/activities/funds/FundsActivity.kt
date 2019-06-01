package br.com.allanlarangeiras.desafioorama.activities.funds

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import br.com.allanlarangeiras.desafioorama.R
import br.com.allanlarangeiras.desafioorama.activities.funds.adapters.ListFundsTabAdapter
import br.com.allanlarangeiras.desafioorama.activities.funds.fragments.FundsListFragment
import br.com.allanlarangeiras.desafioorama.activities.funds.fragments.TopFundsFragment
import br.com.allanlarangeiras.desafioorama.model.dto.Fund
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_funds.*
import kotlinx.android.synthetic.main.include_app_bar.*

class FundsActivity : AppCompatActivity() {

    private var funds:List<Fund> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_funds)

        val extras = intent.extras
        val jsonString = extras?.getString("funds")
        this.funds = Gson().fromJson(jsonString, funds::class.java)

        Log.i(this::class.java.simpleName, jsonString)

        toolbar.title = getText(R.string.funds)
        setSupportActionBar(toolbar)

        val bundle = Bundle()
        bundle.putString("funds", jsonString)

        val topFundsFragment = TopFundsFragment()
        topFundsFragment.arguments = bundle
        val fundsListFragment = FundsListFragment()
        fundsListFragment.arguments = bundle

        val tabAdapter = ListFundsTabAdapter(supportFragmentManager)
        tabAdapter.addFragment(topFundsFragment, getString(R.string.top_funds))
        tabAdapter.addFragment(fundsListFragment, getString(R.string.funds_list))

        pager.adapter = tabAdapter
        tab.setupWithViewPager(pager)
        tab.getTabAt(1)?.select()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onBackPressed() {
        finishAffinity()
    }
}
