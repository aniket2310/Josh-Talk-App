package com.example.joshtalk.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.example.joshtalk.audioSensor.AudioSensor
import com.example.joshtalk.platform.AudioPlayer
import com.example.joshtalk.platform.AudioRecorder
import com.example.joshtalk.platform.getDatabaseBuilder
import com.example.joshtalk.repository.TaskRepository
import com.example.joshtalk.viewModel.TextTaskViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import com.example.joshtalk.repository.TextTaskRepository
import com.example.joshtalk.room.AppDatabase
import com.example.joshtalk.viewModel.ImageTaskViewModel
import com.example.joshtalk.viewModel.PhotoTaskViewModel
import com.example.joshtalk.viewModel.TaskHistoryViewModel


val appModule = module {

    single<AppDatabase> {
        getDatabaseBuilder()
            .setDriver(BundledSQLiteDriver())
            .build()
    }

    // 2. DAO
    single { get<AppDatabase>().taskDao() }


    single { TextTaskRepository() }

    single { TaskRepository(get()) }


    factory { AudioSensor() }

    factory { AudioRecorder() }
    factory { AudioPlayer() }

    factory {
        TextTaskViewModel(
            repo = get(),
            repository = get(),
            audioRecorder = get(),
            audioPlayer = get()
        )
    }
    factory { PhotoTaskViewModel(get(),get(),get()) }
    factory { ImageTaskViewModel(get(),get(),get()) }

    factory { TaskHistoryViewModel(get()) }


}


fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(appModule)
}