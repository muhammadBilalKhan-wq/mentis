package com.socialnetwork.mentis.core.domain.usecase

interface UseCase<in P, out R> {
    operator fun invoke(params: P): R
}