// Generated by Dagger (https://dagger.dev).
package com.cheesejuice.core.common.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import kotlinx.coroutines.CoroutineDispatcher;

@ScopeMetadata
@QualifierMetadata("com.cheesejuice.core.common.di.DispatcherIO")
@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class HiltCommon_ProvidesDispatcherIOFactory implements Factory<CoroutineDispatcher> {
  @Override
  public CoroutineDispatcher get() {
    return providesDispatcherIO();
  }

  public static HiltCommon_ProvidesDispatcherIOFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static CoroutineDispatcher providesDispatcherIO() {
    return Preconditions.checkNotNullFromProvides(HiltCommon.INSTANCE.providesDispatcherIO());
  }

  private static final class InstanceHolder {
    private static final HiltCommon_ProvidesDispatcherIOFactory INSTANCE = new HiltCommon_ProvidesDispatcherIOFactory();
  }
}