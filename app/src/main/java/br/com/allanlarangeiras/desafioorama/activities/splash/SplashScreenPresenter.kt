package br.com.allanlarangeiras.desafioorama.activities.splash

import br.com.allanlarangeiras.desafioorama.model.dto.Fund
import br.com.allanlarangeiras.desafioorama.model.dto.Funds
import br.com.allanlarangeiras.desafioorama.services.FundsService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SplashScreenPresenter(
    private val activity: SplashScreenActivity) {

    fun getFunds() {
        activity.showProgressBar()
        FundsService.getFunds()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doAfterTerminate({ activity.hideProgressBar() })
            .doOnNext { funds ->
                Funds.all = funds
                activity.goToHome()
            }
            .doOnError {error ->
                activity.treatError(error)
            }
            .subscribe()
    }


}