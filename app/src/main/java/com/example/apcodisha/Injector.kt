package com.example.apcodisha

import android.content.Context


interface ViewModelFactoryProvider {

    fun provideViewModelFactory(context: Context): RegistrationViewModelFactory

}


val Injector: ViewModelFactoryProvider
    get() = currentInjector

@Volatile
private var currentInjector: ViewModelFactoryProvider =
    DefaultViewModelProvider

private object DefaultViewModelProvider : ViewModelFactoryProvider {
    private fun getRegRepository(context: Context): RegistrationRepository {
        return RegistrationRepository.getInstance(
            apecDao(context),
            RegService()
        )
    }

    private fun RegService() = NetworkService()

    private fun apecDao(context: Context) =
        AppDatabase.getInstance(context.applicationContext).apecDao()


    override fun provideViewModelFactory(context: Context): RegistrationViewModelFactory {
        val repository = getRegRepository(context)
        return RegistrationViewModelFactory(repository)
    }
}