package it.maggioli.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import io.github.jhipster.config.cache.PrefixedKeyGenerator;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {
    private GitProperties gitProperties;
    private BuildProperties buildProperties;
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, it.maggioli.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, it.maggioli.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, it.maggioli.domain.User.class.getName());
            createCache(cm, it.maggioli.domain.Authority.class.getName());
            createCache(cm, it.maggioli.domain.User.class.getName() + ".authorities");
            createCache(cm, it.maggioli.domain.Pratica.class.getName());
            createCache(cm, it.maggioli.domain.Pratica.class.getName() + ".idPraticas");
            createCache(cm, it.maggioli.domain.Task.class.getName());
            createCache(cm, it.maggioli.domain.Task.class.getName() + ".idTasks");
            createCache(cm, it.maggioli.domain.TemplatePratica.class.getName());
            createCache(cm, it.maggioli.domain.TemplatePratica.class.getName() + ".idTemplatePraticas");
            createCache(cm, it.maggioli.domain.TemplateTask.class.getName());
            createCache(cm, it.maggioli.domain.TemplateTask.class.getName() + ".idTemplateTasks");
            createCache(cm, it.maggioli.domain.AllegatoTemplateTask.class.getName());
            createCache(cm, it.maggioli.domain.TipoAllegato.class.getName());
            createCache(cm, it.maggioli.domain.TipoAllegato.class.getName() + ".idTipoAllegatoes");
            createCache(cm, it.maggioli.domain.AllegatoTask.class.getName());
            createCache(cm, it.maggioli.domain.AllegatoTask.class.getName() + ".idAllegatoTasks");
            createCache(cm, it.maggioli.domain.PrevisioneTask.class.getName());
            createCache(cm, it.maggioli.domain.PrevisioneTask.class.getName() + ".idTaskRefs");
            createCache(cm, it.maggioli.domain.PrevisioneAttivita.class.getName());
            createCache(cm, it.maggioli.domain.PrevisioneEvento.class.getName());
            createCache(cm, it.maggioli.domain.PrevisioneEvento.class.getName() + ".idTaskRefs");
            createCache(cm, it.maggioli.domain.NotaTask.class.getName());
            createCache(cm, it.maggioli.domain.NotaPratica.class.getName());
            createCache(cm, it.maggioli.domain.ConsuntivoTask.class.getName());
            createCache(cm, it.maggioli.domain.CondivisionePratica.class.getName());
            createCache(cm, it.maggioli.domain.AssegnazioneTask.class.getName());
            createCache(cm, it.maggioli.domain.AssegnazioneTask.class.getName() + ".idUserConcedentes");
            createCache(cm, it.maggioli.domain.Invito.class.getName());
            createCache(cm, it.maggioli.domain.Invito.class.getName() + ".idInvitos");
            createCache(cm, it.maggioli.domain.Invitato.class.getName());
            createCache(cm, it.maggioli.domain.UserPersona.class.getName());
            createCache(cm, it.maggioli.domain.UserPersona.class.getName() + ".idUserPersonas");
            createCache(cm, it.maggioli.domain.InvitoPratica.class.getName());
            createCache(cm, it.maggioli.domain.InvitoAttivita.class.getName());
            createCache(cm, it.maggioli.domain.InvitoEvento.class.getName());
            createCache(cm, it.maggioli.domain.RappresentanzaPratica.class.getName());
            createCache(cm, it.maggioli.domain.Persona.class.getName());
            createCache(cm, it.maggioli.domain.Persona.class.getName() + ".idPersonas");
            createCache(cm, it.maggioli.domain.PersonaFisica.class.getName());
            createCache(cm, it.maggioli.domain.PersonaFisica.class.getName() + ".idPersonaFisicas");
            createCache(cm, it.maggioli.domain.Organizzazione.class.getName());
            createCache(cm, it.maggioli.domain.RuoloOrganizzazione.class.getName());
            createCache(cm, it.maggioli.domain.NotePersona.class.getName());
            createCache(cm, it.maggioli.domain.DatiContabili.class.getName());
            createCache(cm, it.maggioli.domain.IndirizzoPersona.class.getName());
            createCache(cm, it.maggioli.domain.EmailPersona.class.getName());
            createCache(cm, it.maggioli.domain.TagPersona.class.getName());
            createCache(cm, it.maggioli.domain.TelefonoPersona.class.getName());
            createCache(cm, it.maggioli.domain.StudioProfessionale.class.getName());
            createCache(cm, it.maggioli.domain.StudioProfessionale.class.getName() + ".idStudioProfessionales");
            createCache(cm, it.maggioli.domain.RisorseDisponibili.class.getName());
            createCache(cm, it.maggioli.domain.Ordine.class.getName());
            createCache(cm, it.maggioli.domain.Ordine.class.getName() + ".idOrdines");
            createCache(cm, it.maggioli.domain.LineaOrdine.class.getName());
            createCache(cm, it.maggioli.domain.Prodotto.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache == null) {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

    @Autowired(required = false)
    public void setGitProperties(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @Autowired(required = false)
    public void setBuildProperties(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
    }
}
