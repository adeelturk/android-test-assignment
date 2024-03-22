package com.example.shacklehotelbuddy.data.mapper.base


/**
 * Provide the Implementation to map Network Dto to Db Dto and DbDto to AppDto
 * [Entity] is Variant for Network Dto
 * [DomainModel] is  Variant for App Dto
 */

abstract class DataMapper<in Entity,out DomainModel> {

    abstract fun mapToDomainModel(serverResponseObj:Entity):DomainModel
    open fun mapToDomainModel(cacheDataList: List<Entity>): List<DomainModel> {
        return cacheDataList.map {

            mapToDomainModel(it)
        }

    }



}