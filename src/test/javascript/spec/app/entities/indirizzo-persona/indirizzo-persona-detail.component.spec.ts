import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { IndirizzoPersonaDetailComponent } from 'app/entities/indirizzo-persona/indirizzo-persona-detail.component';
import { IndirizzoPersona } from 'app/shared/model/indirizzo-persona.model';

describe('Component Tests', () => {
  describe('IndirizzoPersona Management Detail Component', () => {
    let comp: IndirizzoPersonaDetailComponent;
    let fixture: ComponentFixture<IndirizzoPersonaDetailComponent>;
    const route = ({ data: of({ indirizzoPersona: new IndirizzoPersona(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [IndirizzoPersonaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(IndirizzoPersonaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(IndirizzoPersonaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load indirizzoPersona on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.indirizzoPersona).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
