package com.paroquia_santo_afonso.sep.SEP.modules.equipista.model;

import lombok.Getter;

@Getter
public enum Profissao {
    ADMINISTRADOR("Administrador/a"),
    AGENTE_ADMINISTRATIVO("Agente Administrativo"),
    ADVOGADO("Advogado/a"),
    ALMOXARIFE("Almoxarife"),
    ARQUITETO("Arquiteto/a"),
    ARTESAO("Artesã/ão"),
    CABELEIREIRO("Cabeleireiro/a"),
    BIBLIOTECARIO("Bibliotecário/a"),
    CIENTISTA_DA_COMPUTACAO("Cientista da Computação"),
    COMERCIANTE("Comerciante"),
    CONTADOR("Contador/a"),
    DENTISTA("Dentista"),
    ECONOMISTA("Economista"),
    EDUCADOR_FISICO("Educador/a Físico/a"),
    ENFERMEIRO("Enfermeiro/a"),
    ENGENHEIRO_AMBIENTAL("Engenheiro/a Ambiental"),
    ENGENHEIRO_CIVIL("Engenheiro/a Civil"),
    ENGENHEIRO_ELETRICO("Engenheiro/a Elétrico"),
    ENGENHEIRO_MECANICO("Engenheiro/a Mecânico"),
    ENGENHEIRO_OUTRA_ESPECIALIDADE("Engenheiro/a outra especialidade"),
    FILOSOFO("Filósofo/a"),
    FISIOTERAPEUTA("Fisioterapeuta"),
    JORNALISTA("Jornalista"),
    JUIZ("Juiz/a"),
    MEDICO_CARDIOLOGISTA("Médico/a - cardiologista"),
    MEDICO_CLINICO_GERAL("Médico/a - clínico geral"),
    MEDICO_DERMATOLOGISTA("Médico/a - dermatologista"),
    MEDICO_ENDOCRINOLOGISTA("Médico/a - endocrinologista"),
    MEDICO_GASTROENTEROLOGISTA("Médico/a - gastroenterologista"),
    MEDICO_GERIATRA("Médico/a - geriatra"),
    MEDICO_GINECOLOGISTA_E_OBSTETRA("Médico/a - ginecologista e obstetra"),
    MEDICO_MASTOLOGISTA("Médico/a - mastologista"),
    MEDICO_OFTALMOLOGISTA("Médico/a - oftalmologista"),
    MEDICO_ORTOPEDISTA("Médico/a - ortopedista"),
    MEDICO_OTORRINOLARINGOLOGISTA("Médico/a - otorrinolaringologista"),
    MEDICO_PEDIATRA("Médico/a - pediatra"),
    MEDICO_PSIQUIATRA("Médico/a - psiquiatra"),
    MEDICO_UROLOGISTA("Médico/a - urologista"),
    MEDICO_OUTRA_ESPECIALIDADE("Médico/a - outra especialidade"),
    NUTRICIONISTA("Nutricionista"),
    POLICIAL("Policial"),
    PROCURADOR("Procurador/a"),
    PROFESSOR("Professor/a"),
    PROMOTOR("Promotor/a"),
    PSICOLOGO("Psicólogo/a"),
    PUBLICITARIO("Publicitário/a"),
    SOCIOLOGO("Sociólogo/a"),
    TECNICO_ADMINISTRATIVO("Técnico Administrativo"),
    TECNICO_DE_INFORMATICA("Técnico de Informática"),
    TECNICO_JUDICIARIO("Técnico Judiciário"),
    TECNICO_DE_ENFERMAGEM("Técnico/a de Enfermagem"),
    VENDEDOR("Vendedor/a"),
    VETERINARIO("Veterinário/a"),
    OUTRA("Outra");

    private final String title;

    Profissao(String title) {
        this.title = title;
    }
}
