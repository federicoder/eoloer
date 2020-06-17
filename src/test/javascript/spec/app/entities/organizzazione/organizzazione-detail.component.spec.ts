import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { OrganizzazioneDetailComponent } from 'app/entities/organizzazione/organizzazione-detail.component';
import { Organizzazione } from 'app/shared/model/organizzazione.model';

describe('Component Tests', () => {
  describe('Organizzazione Management Detail Component', () => {
    let comp: OrganizzazioneDetailComponent;
    let fixture: ComponentFixture<OrganizzazioneDetailComponent>;
    const route = ({ data: of({ organizzazione: new Organizzazione(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [OrganizzazioneDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(OrganizzazioneDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OrganizzazioneDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load organizzazione on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.organizzazione).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
