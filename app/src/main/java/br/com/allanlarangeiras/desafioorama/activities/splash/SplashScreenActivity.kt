package br.com.allanlarangeiras.desafioorama.activities.splash

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import br.com.allanlarangeiras.desafioorama.R
import br.com.allanlarangeiras.desafioorama.activities.funds.FundsActivity
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity: AppCompatActivity() {

    private val splashScreenPresenter = SplashScreenPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        splashScreenPresenter.getFunds()
    }

    fun goToHome() {
        val goToHome = Intent(this, FundsActivity::class.java)
        startActivity(goToHome)
    }

    fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        progressBar.visibility = View.INVISIBLE
    }

    fun treatError(error: Throwable?) {
        Log.e(this::class.java.simpleName, error?.message)
        AlertDialog.Builder(this)
            .setTitle("Erro")
            .setMessage("Houve um erro ao carregar os dados do servidor.")
            .setPositiveButton("Tentar novamente") { dialog, which ->
                splashScreenPresenter.getFunds()
            }
            .setNegativeButton("Fechar o aplicativo") { dialog, which ->
                finishAffinity()
            }
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }
}