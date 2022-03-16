package com.example.movvy.di.module

import com.example.movvy.App
import dagger.Component
import dagger.internal.Preconditions
import javax.inject.Singleton


@Component(modules = [AppModule::class,RepositoryModule::class],)
@Singleton
abstract class ApplicationComponent {
    abstract fun inject(daggerArchApplication: App)
    abstract fun mainScreenComponent():MainScreenComponent


    companion object {
        @Volatile
        private var instance: ApplicationComponent? = null

        fun get(): ApplicationComponent {
            return Preconditions.checkNotNull(instance,
                "AppComponent is not initialized yet. Call init first.")!!
        }

        fun init(component: ApplicationComponent) {
            require(instance == null) { "AppComponent is already initialized." }
            instance = component
        }
    }
}