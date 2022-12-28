package ir.majazi.sabtamval.di

import com.example.global.data.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton

@InstallIn(ViewModelComponent::class)
@Module
class AppModule {

//    @Singleton
//    @Provides
//    fun provideSettingRepository(
//        settingRepository: SettingRepository
//    ): Repository.Setting = settingRepository

}