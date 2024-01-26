package com.gongsung.company

import com.gongsung.company.persist.CommandCompanyPersist
import com.gongsung.company.persist.QueryCompanyPersist
import com.gonsung.company.Company
import com.gonsung.company.CompanyIdentity
import com.gonsung.company.CompanyProps
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.mock


class CompanyServiceTest {


    @Test
    fun `id로 Company 조회가 가능하다`() {
        //given
        val id = 1L
        val expected = Company.of(
            companyIdentity = CompanyIdentity.of(id),
            props = CompanyProps.of(
                name = "Gongsung",
                address = "seoul",
            ),
        )
        val queryCompanyPersist = mock<QueryCompanyPersist>() {
            on { getCompanyById(id) } doReturn expected
        }
        val commandCompanyPersist = mock<CommandCompanyPersist>()
        val sut = CompanyService(queryCompanyPersist, commandCompanyPersist)

        // when
        val result = sut.getCompanyById(CompanyIdentity.of(id))

        //then
        Assertions.assertEquals(id, result.companyIdentity)
        Assertions.assertEquals(expected, result)

    }

    @Test
    fun `Company를 조회할 때 데이터가 없으면 예외를 반환한다`() {
        //given
        val id = 1L
        val queryCompanyPersist = mock<QueryCompanyPersist>() {
            on { getCompanyById(id) } doThrow RuntimeException("[Company-${id}] Not Exist")
        }
        val commandCompanyPersist = mock<CommandCompanyPersist>()
        val sut = CompanyService(queryCompanyPersist, commandCompanyPersist)

        // when, then
        Assertions.assertThrows(Exception::class.java) {
            sut.getCompanyById(CompanyIdentity.of(id))
        }
    }

    @Test
    fun `Company를 저장할 수 있다`() {
        //given
        val companyProps = CompanyProps.of(
            name = "Gongsung",
            address = "seoul",
        )

        val expected = Company.of(
            companyIdentity = CompanyIdentity.of(1L),
            props = companyProps,
        )

        val queryCompanyPersist = mock<QueryCompanyPersist>() {
            on { getCompanyById(1L) } doReturn expected
        }
        val commandCompanyPersist = mock<CommandCompanyPersist>() {
            on { createCompany(companyProps) } doReturn expected
        }
        val sut = CompanyService(queryCompanyPersist, commandCompanyPersist)

        //when
        sut.creatCompany(companyProps)
        val result = sut.getCompanyById(CompanyIdentity.of(1L))

        //then
        Assertions.assertEquals(1L, result.companyIdentity)
        Assertions.assertEquals(expected, result)

    }

    @Test
    fun `Company의 id로 삭제가 가능하다`() {
        //given
        val id = 1L
        val commandCompanyPersist = mock<CommandCompanyPersist>() {
            on { deleteCompanyById(id) } doReturn true
        }
        val queryCompanyPersist = mock<QueryCompanyPersist>() {
            on { getCompanyById(id) } doThrow RuntimeException("[Company-${id}] Not Exist")
        }
        val sut = CompanyService(queryCompanyPersist, commandCompanyPersist)

        //when
        val result = sut.deleteCompany(CompanyIdentity.of(id))

        //then
        Assertions.assertTrue(result)
    }

    @Test
    fun `Company의 정보를 수정할 수 있다`() {
        //given
        val id = 1L
        val before = Company.of(
            companyIdentity = CompanyIdentity.of(id),
            props = CompanyProps.of(
                name = "gongsung",
                address = "seoul",
            ),
        )
        val after = Company.of(
            companyIdentity = CompanyIdentity.of(id),
            props = CompanyProps.of(
                name = "Tong-Keun",
                address = "Deagu",
            ),
        )
        val commandCompanyPersist = mock<CommandCompanyPersist>() {
            on { updateCompany(after) } doReturn after
        }
        val queryCompanyPersist = mock<QueryCompanyPersist>() {
            on { getCompanyById(id) } doReturn after
        }
        val sut = CompanyService(queryCompanyPersist, commandCompanyPersist)

        //when
        val result = sut.updateCompany(after)

        //then
        Assertions.assertEquals(after, result)
        Assertions.assertNotEquals(before, result)

    }
}
