package com.naple.android.one_day_one_motivation.view.splash.presenter

interface SplashContract {

    interface View{

    }

    interface Presenter{
        fun loginInsertOrUpdate(uuid : String?)
    }

}