package com.example.restobar1appstgocity.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "consumo")
class ConsumosEntity (
 @PrimaryKey(autoGenerate = true)
 @NotNull
 val id: Int = 0,
 val nomproducto: String,
 val valortotal: Int,



         )
