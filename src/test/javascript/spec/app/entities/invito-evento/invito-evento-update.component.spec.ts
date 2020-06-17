import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { InvitoEventoUpdateComponent } from 'app/entities/invito-evento/invito-evento-update.component';
import { InvitoEventoService } from 'app/entities/invito-evento/invito-evento.service';
import { InvitoEvento } from 'app/shared/model/invito-evento.model';

describe('Component Tests', () => {
  describe('InvitoEvento Management Update Component', () => {
    let comp: InvitoEventoUpdateComponent;
    let fixture: ComponentFixture<InvitoEventoUpdateComponent>;
    let service: InvitoEventoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [InvitoEventoUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(InvitoEventoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(InvitoEventoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(InvitoEventoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new InvitoEvento(123);
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
        const entity = new InvitoEvento();
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
