/*
 * Copyright 2020 Jeremy Jamet / Kunzisoft.
 *
 * This file is part of KeePassDX.
 *
 *  KeePassDX is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  KeePassDX is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with KeePassDX.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.kunzisoft.keepass.utils

class UnsignedInt(private var unsignedValue: Int) {

    constructor(unsignedValue: UnsignedInt) : this(unsignedValue.toInt())

    /**
     * Get the int value
     */
    fun toInt(): Int {
        return unsignedValue
    }

    /**
     * Convert an unsigned Integer to Long
     */
    fun toLong(): Long {
        return unsignedValue.toLong() and INT_TO_LONG_MASK
    }

    /**
     * Convert an unsigned Integer to Byte
     */
    fun toByte(): Byte {
        return (unsignedValue and 0xFF).toByte()
    }

    override fun toString():String {
        return toLong().toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UnsignedInt

        if (unsignedValue != other.unsignedValue) return false

        return true
    }

    override fun hashCode(): Int {
        return unsignedValue
    }

    companion object {
        private const val INT_TO_LONG_MASK: Long = 0xffffffffL
        private const val UINT_MAX_VALUE: Long = 4294967295L // 2^32

        val MAX_VALUE = UnsignedInt(UINT_MAX_VALUE.toInt())

        /**
         * Convert a byte to an unsigned byte
         */
        fun fromByte(value: Byte): UnsignedInt {
            return UnsignedInt(value.toInt() and 0xFF)
        }

        @Throws(NumberFormatException::class)
        fun fromLong(value: Long): UnsignedInt {
            if (value > UINT_MAX_VALUE)
                throw NumberFormatException("UInt value > $UINT_MAX_VALUE")
            return UnsignedInt(value.toInt())
        }
    }
}