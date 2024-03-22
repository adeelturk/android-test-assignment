package com.example.shacklehotelbuddy.data.mapper.base


/**
 * Provide the Implementation to map Network Dto to Db Dto and DbDto to AppDto
 * [Entity] is Variant for Network Dto
 * [DomainModel] is  Variant for App Dto
 */

abstract class RequestDataMapper<in DomainModel,out Entity> {

    abstract fun mapToEntity(appRequest:DomainModel):Entity

}