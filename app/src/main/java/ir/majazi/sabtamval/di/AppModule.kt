package ir.majazi.sabtamval.di

import com.example.global.data.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ir.majazi.sabtamval.data.AppRepository
import javax.inject.Singleton

@InstallIn(ViewModelComponent::class)
@Module
class AppModule {

//    @Singleton
//    @Provides
//    fun provideSettingRepository(
//        settingRepository: SettingRepository
//    ): Repository.Setting = settingRepository

    @Singleton
    @Provides
    fun provideAppRepository(
        appRepository: AppRepository
    ): Repository.App = appRepository
}