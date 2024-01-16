package com.gongsung.port

import com.gonsung.company.Company


// Repository에 대한 Interface. 해당 패키지에 있는 인터페이스들은 다른곳으로 옮기는게 좋아보임. 예를 들어 use-case 모듈로? use-case 모듈의 이름이 애매한것같음

interface LookCompanyPort {
    fun getCompanyById(id: Long): Company
}
