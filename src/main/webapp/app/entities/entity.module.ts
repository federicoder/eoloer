import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'pratica',
        loadChildren: () => import('./pratica/pratica.module').then(m => m.EoloprjPraticaModule),
      },
      {
        path: 'task',
        loadChildren: () => import('./task/task.module').then(m => m.EoloprjTaskModule),
      },
      {
        path: 'template-pratica',
        loadChildren: () => import('./template-pratica/template-pratica.module').then(m => m.EoloprjTemplatePraticaModule),
      },
      {
        path: 'template-task',
        loadChildren: () => import('./template-task/template-task.module').then(m => m.EoloprjTemplateTaskModule),
      },
      {
        path: 'allegato-template-task',
        loadChildren: () => import('./allegato-template-task/allegato-template-task.module').then(m => m.EoloprjAllegatoTemplateTaskModule),
      },
      {
        path: 'tipo-allegato',
        loadChildren: () => import('./tipo-allegato/tipo-allegato.module').then(m => m.EoloprjTipoAllegatoModule),
      },
      {
        path: 'allegato-task',
        loadChildren: () => import('./allegato-task/allegato-task.module').then(m => m.EoloprjAllegatoTaskModule),
      },
      {
        path: 'previsione-task',
        loadChildren: () => import('./previsione-task/previsione-task.module').then(m => m.EoloprjPrevisioneTaskModule),
      },
      {
        path: 'previsione-attivita',
        loadChildren: () => import('./previsione-attivita/previsione-attivita.module').then(m => m.EoloprjPrevisioneAttivitaModule),
      },
      {
        path: 'previsione-evento',
        loadChildren: () => import('./previsione-evento/previsione-evento.module').then(m => m.EoloprjPrevisioneEventoModule),
      },
      {
        path: 'nota-task',
        loadChildren: () => import('./nota-task/nota-task.module').then(m => m.EoloprjNotaTaskModule),
      },
      {
        path: 'nota-pratica',
        loadChildren: () => import('./nota-pratica/nota-pratica.module').then(m => m.EoloprjNotaPraticaModule),
      },
      {
        path: 'consuntivo-task',
        loadChildren: () => import('./consuntivo-task/consuntivo-task.module').then(m => m.EoloprjConsuntivoTaskModule),
      },
      {
        path: 'condivisione-pratica',
        loadChildren: () => import('./condivisione-pratica/condivisione-pratica.module').then(m => m.EoloprjCondivisionePraticaModule),
      },
      {
        path: 'assegnazione-task',
        loadChildren: () => import('./assegnazione-task/assegnazione-task.module').then(m => m.EoloprjAssegnazioneTaskModule),
      },
      {
        path: 'invito',
        loadChildren: () => import('./invito/invito.module').then(m => m.EoloprjInvitoModule),
      },
      {
        path: 'invitato',
        loadChildren: () => import('./invitato/invitato.module').then(m => m.EoloprjInvitatoModule),
      },
      {
        path: 'user-persona',
        loadChildren: () => import('./user-persona/user-persona.module').then(m => m.EoloprjUserPersonaModule),
      },
      {
        path: 'invito-pratica',
        loadChildren: () => import('./invito-pratica/invito-pratica.module').then(m => m.EoloprjInvitoPraticaModule),
      },
      {
        path: 'invito-attivita',
        loadChildren: () => import('./invito-attivita/invito-attivita.module').then(m => m.EoloprjInvitoAttivitaModule),
      },
      {
        path: 'invito-evento',
        loadChildren: () => import('./invito-evento/invito-evento.module').then(m => m.EoloprjInvitoEventoModule),
      },
      {
        path: 'rappresentanza-pratica',
        loadChildren: () =>
          import('./rappresentanza-pratica/rappresentanza-pratica.module').then(m => m.EoloprjRappresentanzaPraticaModule),
      },
      {
        path: 'persona',
        loadChildren: () => import('./persona/persona.module').then(m => m.EoloprjPersonaModule),
      },
      {
        path: 'persona-fisica',
        loadChildren: () => import('./persona-fisica/persona-fisica.module').then(m => m.EoloprjPersonaFisicaModule),
      },
      {
        path: 'organizzazione',
        loadChildren: () => import('./organizzazione/organizzazione.module').then(m => m.EoloprjOrganizzazioneModule),
      },
      {
        path: 'ruolo-organizzazione',
        loadChildren: () => import('./ruolo-organizzazione/ruolo-organizzazione.module').then(m => m.EoloprjRuoloOrganizzazioneModule),
      },
      {
        path: 'note-persona',
        loadChildren: () => import('./note-persona/note-persona.module').then(m => m.EoloprjNotePersonaModule),
      },
      {
        path: 'dati-contabili',
        loadChildren: () => import('./dati-contabili/dati-contabili.module').then(m => m.EoloprjDatiContabiliModule),
      },
      {
        path: 'indirizzo-persona',
        loadChildren: () => import('./indirizzo-persona/indirizzo-persona.module').then(m => m.EoloprjIndirizzoPersonaModule),
      },
      {
        path: 'email-persona',
        loadChildren: () => import('./email-persona/email-persona.module').then(m => m.EoloprjEmailPersonaModule),
      },
      {
        path: 'tag-persona',
        loadChildren: () => import('./tag-persona/tag-persona.module').then(m => m.EoloprjTagPersonaModule),
      },
      {
        path: 'telefono-persona',
        loadChildren: () => import('./telefono-persona/telefono-persona.module').then(m => m.EoloprjTelefonoPersonaModule),
      },
      {
        path: 'studio-professionale',
        loadChildren: () => import('./studio-professionale/studio-professionale.module').then(m => m.EoloprjStudioProfessionaleModule),
      },
      {
        path: 'risorse-disponibili',
        loadChildren: () => import('./risorse-disponibili/risorse-disponibili.module').then(m => m.EoloprjRisorseDisponibiliModule),
      },
      {
        path: 'ordine',
        loadChildren: () => import('./ordine/ordine.module').then(m => m.EoloprjOrdineModule),
      },
      {
        path: 'linea-ordine',
        loadChildren: () => import('./linea-ordine/linea-ordine.module').then(m => m.EoloprjLineaOrdineModule),
      },
      {
        path: 'prodotto',
        loadChildren: () => import('./prodotto/prodotto.module').then(m => m.EoloprjProdottoModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EoloprjEntityModule {}
