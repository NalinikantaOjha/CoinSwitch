package com.masai.nalini.ui.adapter.listner

import com.masai.nalini.local.transaction.TransactionEntity

interface OnTransctionListner {
    fun OnTrans(transactionEntity: TransactionEntity)
}