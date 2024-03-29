package com.danisbana.danisbanaapp.core.repo

import com.danisbana.danisbanaapp.core.model.agreement.Agreement
import com.danisbana.danisbanaapp.domain.repo.FirebaseConfigRepo
import com.danisbana.danisbanaapp.domain.service.FirebaseConfigService
import com.danisbana.danisbanaapp.domain.service.FirebaseDatabaseService
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class FirebaseConfigRepoImpl(
    private var configService: FirebaseConfigService,
) : FirebaseConfigRepo {
    override suspend fun getAgreementConfigAsync(): Deferred<Result<Agreement>> {
        return withContext(Dispatchers.IO) {
            var result: Result<Agreement>
            return@withContext async {
                try {
                    result = configService.getAgreementConfig()
                    return@async result
                } catch (e: Exception) {
                    result = Result.failure(e)
                    return@async result
                }
            }
        }
    }
}