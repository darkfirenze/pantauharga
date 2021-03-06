package com.pantau.user

class AuthUser {

    transient springSecurityService

    String username
    String password
    String email
    String ktp
    String nohp
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired
    List<AuthRole> roles

    static transients = ['springSecurityService', 'roles']

    static constraints = {
        username blank: false, unique: true
        password blank: false
        ktp nullable: true, blank: true
        email nullable: true, blank: true, unique: true, email: true
        nohp unique: true
        roles bindable: true
    }

    static mapping = {
        password column: '`password`'
    }


    Set<AuthRole> getAuthorities() {
        AuthUserAuthRole.findAllByAuthUser(this).collect { it.authRole }
    }

    def beforeInsert() {
        encodePassword()
    }

    def beforeUpdate() {
        if (isDirty('password')) {
            encodePassword()
        }
    }

    protected void encodePassword() {
        password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
    }

    String toString() {
        username
    }
}