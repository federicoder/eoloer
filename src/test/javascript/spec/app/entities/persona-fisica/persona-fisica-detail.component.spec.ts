import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { PersonaFisicaDetailComponent } from 'app/entities/persona-fisica/persona-fisica-detail.component';
import { PersonaFisica } from 'app/shared/model/persona-fisica.model';

describe('Component Tests', () => {
  describe('PersonaFisica Management Detail Component', () => {
    let comp: PersonaFisicaDetailComponent;
    let fixture: ComponentFixture<PersonaFisicaDetailComponent>;
    const route = ({ data: of({ personaFisica: new PersonaFisica(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [PersonaFisicaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(PersonaFisicaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PersonaFisicaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load personaFisica on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.personaFisica).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
