package com.apripachkin.tuneinbrowser.domain

import com.apripachkin.tuneinbrowser.data.Outline

sealed class ApiResponse
data class Success(val items: List<Outline>) : ApiResponse()
object Loading : ApiResponse()
object Fail : ApiResponse()
