package br.com.allanlarangeiras.desafioorama.activities.funds

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import br.com.allanlarangeiras.desafioorama.R
import br.com.allanlarangeiras.desafioorama.activities.funds.adapters.ListFundsTabAdapter
import br.com.allanlarangeiras.desafioorama.activities.funds.dialogs.InfoBottomSheetDialogFragment
import br.com.allanlarangeiras.desafioorama.activities.funds.dialogs.InfoGeneralBottomSheetDialogFragmnet
import br.com.allanlarangeiras.desafioorama.activities.funds.fragments.FundsListFragment
import br.com.allanlarangeiras.desafioorama.activities.funds.fragments.TopFundsFragment
import br.com.allanlarangeiras.desafioorama.model.dto.Fund
import kotlinx.android.synthetic.main.activity_funds.*
import kotlinx.android.synthetic.main.include_app_bar.*

class FundsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_funds)

        toolbar.title = getText(R.string.funds)
        setSupportActionBar(toolbar)

        val topFundsFragment = TopFundsFragment()
        val fundsListFragment = FundsListFragment()

        val tabAdapter = ListFundsTabAdapter(supportFragmentManager)
        tabAdapter.addFragment(topFundsFragment, getString(R.string.top_funds))
        tabAdapter.addFragment(fundsListFragment, getString(R.string.funds_list))

        pager.adapter = tabAdapter
        
        tab.setupWithViewPager(pager)
        tab.getTabAt(1)?.select()
    }

    fun showInfoBottomSheet(fund: Fund) {
        val infoBottomSheet = InfoBottomSheetDialogFragment()
        val bundle = Bundle()
        bundle.putString("macroTitle", fund.specification.fundMacroStrategy.name)
        bundle.putString("macroDescription", fund.specification.fundMacroStrategy.explanation)
        bundle.putString("mainTitle", fund.specification.fundMainStrategy.name)
        bundle.putString("mainDescription", fund.specification.fundMainStrategy.explanation)
        infoBottomSheet.arguments = bundle
        infoBottomSheet.show(supportFragmentManager, infoBottomSheet.tag)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.info -> {
                val infoBottomSheet = InfoGeneralBottomSheetDialogFragmnet()
                infoBottomSheet.show(supportFragmentManager, infoBottomSheet.tag)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        finishAffinity()
    }


}
