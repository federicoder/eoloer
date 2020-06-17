import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { RuoloOrganizzazioneDetailComponent } from 'app/entities/ruolo-organizzazione/ruolo-organizzazione-detail.component';
import { RuoloOrganizzazione } from 'app/shared/model/ruolo-organizzazione.model';

describe('Component Tests', () => {
  describe('RuoloOrganizzazione Management Detail Component', () => {
    let comp: RuoloOrganizzazioneDetailComponent;
    let fixture: ComponentFixture<RuoloOrganizzazioneDetailComponent>;
    const route = ({ data: of({ ruoloOrganizzazione: new RuoloOrganizzazione(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [RuoloOrganizzazioneDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(RuoloOrganizzazioneDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RuoloOrganizzazioneDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load ruoloOrganizzazione on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.ruoloOrganizzazione).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
