import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { TelefonoPersonaDetailComponent } from 'app/entities/telefono-persona/telefono-persona-detail.component';
import { TelefonoPersona } from 'app/shared/model/telefono-persona.model';

describe('Component Tests', () => {
  describe('TelefonoPersona Management Detail Component', () => {
    let comp: TelefonoPersonaDetailComponent;
    let fixture: ComponentFixture<TelefonoPersonaDetailComponent>;
    const route = ({ data: of({ telefonoPersona: new TelefonoPersona(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [TelefonoPersonaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TelefonoPersonaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TelefonoPersonaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load telefonoPersona on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.telefonoPersona).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
