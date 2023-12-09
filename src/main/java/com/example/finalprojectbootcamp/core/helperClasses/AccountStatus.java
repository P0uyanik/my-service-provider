package com.example.finalprojectbootcamp.core.helperClasses;

import jakarta.annotation.Nullable;
import jakarta.persistence.Embeddable;

@Embeddable
public class AccountStatus {

private boolean accessToAccount = true  ;



    public Boolean getAccessToAccount() {
        return accessToAccount;
    }

    public void setAccessToAccount( boolean accessToAccount) {
        this.accessToAccount = accessToAccount;
    }
}
