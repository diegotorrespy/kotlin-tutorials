package com.baeldung.dateTimeToInstant

import org.junit.jupiter.api.Test
import java.time.*
import java.time.format.DateTimeFormatter
import kotlin.test.assertEquals

class DateTimeStringToInstantUnitTest {

    @Test
    fun `given date and time as strings,combine them and parse to localdatetime then use zoneId`(){
        val strDate = "2021-11-25"
        val strTime = "15:20"
        val strDateTime = strDate + "T" + strTime
        val ldt = LocalDateTime.parse(strDateTime)
        val instant: Instant = ldt.atZone(ZoneId.of("Europe/Rome")).toInstant()
        assertEquals("2021-11-25T14:20:00Z", instant.toString())
    }

    @Test
    fun `given date and time strings parse directly to localdatetime then use zoneId`(){
        val strDate = "2021-11-25"
        val strTime = "15:20"
        val ldt = LocalDateTime.of(LocalDate.parse(strDate), LocalTime.parse(strTime))
        val instant: Instant = ldt.atZone(ZoneId.of("Europe/Rome")).toInstant()
        assertEquals("2021-11-25T14:20:00Z", instant.toString())
    }

    @Test
    fun `given date and time strings, parse directly to  zoneddatetime object`(){
        val strDate = "2021-11-25"
        val strTime = "15:20"
        val zdt1 = ZonedDateTime.of(LocalDate.parse(strDate), LocalTime.parse(strTime), ZoneId.of("Europe/Rome"))
        val zdt2 = ZonedDateTime.of(LocalDateTime.of(LocalDate.parse(strDate), LocalTime.parse(strTime)), ZoneId.of("Europe/Rome"))
        val instant1: Instant = zdt1.toInstant()
        val instant2: Instant = zdt2.toInstant()
        assertEquals("2021-11-25T14:20:00Z", instant1.toString())
        assertEquals("2021-11-25T14:20:00Z", instant2.toString())
    }

    @Test
    fun `given date and time strings, use it together with datetimeformatter on zoneddatetime object`(){
        val strDate = "2021-11-25"
        val strTime = "15:20"
        val dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneId.of("Europe/Rome"))
        val zdt = ZonedDateTime.parse(strDate + "T" + strTime, dtf)
        val instant = zdt.toInstant()
        assertEquals("2021-11-25T14:20:00Z", instant.toString())
    }

    @Test
    fun `given date and time strings, parse directly on localdatetime and call toinstant method`(){
        val strDate = "2021-11-25"
        val strTime = "15:20"
        val ldt = LocalDateTime.of(LocalDate.parse(strDate), LocalTime.parse(strTime))
        val instant = ldt.toInstant(ZoneId.of("Europe/Rome").rules.getOffset(ldt))
        assertEquals("2021-11-25T14:20:00Z", instant.toString())
    }
}