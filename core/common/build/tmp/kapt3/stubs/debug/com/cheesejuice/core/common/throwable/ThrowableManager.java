package com.cheesejuice.core.common.throwable;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0011\u0010\u0010\u001a\u00020\u0011H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012J\u000e\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0015J9\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00152\u000e\b\u0002\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00110\u00182\u000e\b\u0002\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00110\u0018H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001aR\u0016\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001b"}, d2 = {"Lcom/cheesejuice/core/common/throwable/ThrowableManager;", "", "()V", "_errorState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/cheesejuice/core/common/throwable/ErrorState;", "errorState", "Lkotlinx/coroutines/flow/StateFlow;", "getErrorState", "()Lkotlinx/coroutines/flow/StateFlow;", "scopeApplication", "Lkotlinx/coroutines/CoroutineScope;", "getScopeApplication", "()Lkotlinx/coroutines/CoroutineScope;", "scopeApplication$delegate", "Lkotlin/Lazy;", "dismissErrorDialog", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendError", "throwable", "", "showErrorDialog", "onConfirm", "Lkotlin/Function0;", "onDismiss", "(Ljava/lang/Throwable;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "common_debug"})
public final class ThrowableManager {
    @org.jetbrains.annotations.NotNull()
    public static final com.cheesejuice.core.common.throwable.ThrowableManager INSTANCE = null;
    private static final kotlinx.coroutines.flow.MutableStateFlow<com.cheesejuice.core.common.throwable.ErrorState> _errorState = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlinx.coroutines.flow.StateFlow<com.cheesejuice.core.common.throwable.ErrorState> errorState = null;
    private static final kotlin.Lazy scopeApplication$delegate = null;
    
    private ThrowableManager() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.cheesejuice.core.common.throwable.ErrorState> getErrorState() {
        return null;
    }
    
    private final kotlinx.coroutines.CoroutineScope getScopeApplication() {
        return null;
    }
    
    public final void sendError(@org.jetbrains.annotations.NotNull()
    java.lang.Throwable throwable) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object showErrorDialog(@org.jetbrains.annotations.NotNull()
    java.lang.Throwable throwable, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onConfirm, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object dismissErrorDialog(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
}