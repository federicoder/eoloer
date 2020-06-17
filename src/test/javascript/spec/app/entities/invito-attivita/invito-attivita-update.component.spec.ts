import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { InvitoAttivitaUpdateComponent } from 'app/entities/invito-attivita/invito-attivita-update.component';
import { InvitoAttivitaService } from 'app/entities/invito-attivita/invito-attivita.service';
import { InvitoAttivita } from 'app/shared/model/invito-attivita.model';

describe('Component Tests', () => {
  describe('InvitoAttivita Management Update Component', () => {
    let comp: InvitoAttivitaUpdateComponent;
    let fixture: ComponentFixture<InvitoAttivitaUpdateComponent>;
    let service: InvitoAttivitaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [InvitoAttivitaUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(InvitoAttivitaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(InvitoAttivitaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(InvitoAttivitaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new InvitoAttivita(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new InvitoAttivita();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
