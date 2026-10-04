package com.rajedev.ainewsapp.di

import androidx.annotation.RestrictTo
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
annotation class IoDispatcher

@Qualifier
@Retention(AnnotationRetention.BINARY)
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
annotation class DefaultDispatcher